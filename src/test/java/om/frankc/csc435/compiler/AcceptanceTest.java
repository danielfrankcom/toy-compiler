package om.frankc.csc435.compiler;

import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class AcceptanceTest {

    private static final ClassLoader CLASS_LOADER = AcceptanceTest.class.getClassLoader();
    private static final String TEST_FOLDER = "acceptance";

    private enum Group {
        accept,
        reject
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

    @Test
    public void testCompilerAccepts() {
        final File[] acceptFiles = getTestFilesForGroup(Group.accept);

        for (File file : acceptFiles) {
            final String path = file.getAbsolutePath();
            final String[] arguments = {path};

            System.out.printf("Testing %s\n", path);

            boolean success = false;
            try {
                Compiler.main(arguments);

                // The above line will not throw an exception if successful,
                // but will also not return a result. We double check that
                // we get past that line for the sake of the unit test.
                success = true;

            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            assertTrue(success);
        }
    }

    @Test
    public void testCompilerRejects() {
        final File[] rejectFiles = getTestFilesForGroup(Group.reject);

        for (File file : rejectFiles) {
            final String path = file.getAbsolutePath();
            final String[] arguments = {path};

            System.out.printf("Testing %s\n", path);

            Throwable exception = null;
            try {
                Compiler.main(arguments);
            } catch (Throwable e) {
                exception = e;
            }

            assertNotNull(exception);
        }
    }

}