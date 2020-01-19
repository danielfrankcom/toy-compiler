package om.frankc.csc435.compiler.ast;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

public abstract class NodeList<E extends AstNode> extends AstNode {

    public NodeList(List<E> elements) {
        assert elements != null;
        mElements = elements;
    }

    private final List<E> mElements;

    public ImmutableList<E> getElements() {
        return ImmutableList.copyOf(mElements);
    }

    @Override
    public abstract void accept(IAstVisitor visitor);

    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof NodeList) {
            final NodeList<?> known = (NodeList<?>) other;
            return Objects.equals(mElements, known.mElements);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(mElements);
    }

}