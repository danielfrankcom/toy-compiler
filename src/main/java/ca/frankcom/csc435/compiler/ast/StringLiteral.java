package ca.frankcom.csc435.compiler.ast;

import ca.frankcom.csc435.compiler.ast.visit.IAstVisitor;

import java.util.Objects;

public class StringLiteral extends Literal {

    public StringLiteral(String value,
                         int lineNumber, int linePosition) {
        super(lineNumber, linePosition);
        assert value != null;
        mValue = value;
    }

    private final String mValue;

    public String getValue() {
        return mValue;
    }

    @Override
    public <T> T accept(IAstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof StringLiteral) {
            final StringLiteral known = (StringLiteral) other;
            return super.equals(known)
                    && Objects.equals(mValue, known.mValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mValue);
    }

}
