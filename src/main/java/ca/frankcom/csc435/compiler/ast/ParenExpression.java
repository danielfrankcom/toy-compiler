package ca.frankcom.csc435.compiler.ast;

import ca.frankcom.csc435.compiler.ast.visit.IAstVisitor;

import java.util.Objects;

public class ParenExpression extends Expression {

    public ParenExpression(Expression expression,
                           int lineNumber, int linePosition) {
        super(lineNumber, linePosition);
        assert expression != null;
        mExpression = expression;
    }

    private final Expression mExpression;

    public Expression getExpression() {
        return mExpression;
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
        if (other instanceof ParenExpression) {
            final ParenExpression known = (ParenExpression) other;
            return super.equals(known)
                    && Objects.equals(mExpression, known.mExpression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mExpression);
    }

}
