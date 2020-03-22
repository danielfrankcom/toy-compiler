package ca.frankcom.csc435.compiler;

import com.google.common.collect.ImmutableMap;
import jasmin.Main;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class IrGenTest {

    private static final ClassLoader CLASS_LOADER = IrGenTest.class.getClassLoader();
    private static final String TEST_FOLDER = "ir";

    private enum Group {
        input,
        output
    }

    /**
     * Get an array of {@link File} objects representing the set of files within the specified testing group
     *
     * @param group A {@link Group} that uniquely identifies the set of files to address.
     * @return The generated {@link File[]}.
     */
    private static File[] getTestFilesForGroup(Group group) {
        final Path combinedPath = Paths.get(TEST_FOLDER, group.name());
        final String relativePath = combinedPath.toFile().getPath();
        final URL rootPath = CLASS_LOADER.getResource(relativePath);
        assertNotNull(rootPath);

        final File root = new File(rootPath.getFile());
        final File[] children = root.listFiles();
        assertNotNull(children);
        assertNotEquals(0, children.length);

        return children;
    }

    /**
     * Uses the first three characters of the filename to produce an {@link ImmutableMap} that links the test number
     * to the {@link File}.
     *
     * @return The generated {@link ImmutableMap}.
     */
    private static ImmutableMap<Integer, File> identifyTestFiles(File[] files) {
        final Map<Integer, File> map = new HashMap<>();

        for (File file : files) {
            final String name = file.getName();
            final String numeric = name.substring(0, 3);
            final int testNumber = Integer.parseInt(numeric);

            map.put(testNumber, file);
        }

        return ImmutableMap.copyOf(map);
    }

    /**
     * Get an {@link ImmutableMap} that links input {@link File} objects to their corresponding output.
     *
     * @return The generated {@link ImmutableMap}.
     */
    private static ImmutableMap<File, File> getTestFiles() {
        final Map<File, File> result = new HashMap<>();

        final File[] inputs = getTestFilesForGroup(Group.input);
        final ImmutableMap<Integer, File> inputMap = identifyTestFiles(inputs);

        final File[] outputs = getTestFilesForGroup(Group.output);
        final ImmutableMap<Integer, File> outputMap = identifyTestFiles(outputs);

        assertEquals(inputMap.size(), outputMap.size());

        for (Integer inputNumber : inputMap.keySet()) {
            assertTrue(outputMap.containsKey(inputNumber));
            final File input = inputMap.get(inputNumber);
            final File output = outputMap.get(inputNumber);

            result.put(input, output);
        }

        return ImmutableMap.copyOf(result);
    }

    @Test
    public void testIrGeneration() throws Exception {

        final ImmutableMap<File, File> testFiles = getTestFiles();

        for (Map.Entry<File, File> entry : testFiles.entrySet()) {
            final File input = entry.getKey();
            final File output = File.createTempFile("compilerTest", null);
            output.deleteOnExit();
            final File expected = entry.getValue();

            final InputStream inputStream = new FileInputStream(input);
            final OutputStream outputStream = new FileOutputStream(output);

            final String inputPath = input.getAbsolutePath();
            final String outputPath = output.getAbsolutePath();
            final String expectedPath = expected.getAbsolutePath();

            System.out.printf("Testing '%s' -> '%s' -> '%s'\n", inputPath, outputPath, expectedPath);

            boolean success = false;
            try {
                Compiler.compile("test", inputStream, null, outputStream,
                        null, null, true);

                // The above line will not throw an exception if successful,
                // but will also not return a result. We double check that
                // we get past that line for the sake of the unit test.
                success = true;

            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            assertTrue(success);
            final String expectedText = FileUtils.readFileToString(expected, Charset.defaultCharset());
            final String actualText = FileUtils.readFileToString(output, Charset.defaultCharset());

            assertEquals(expectedText, actualText);
        }
    }

}
