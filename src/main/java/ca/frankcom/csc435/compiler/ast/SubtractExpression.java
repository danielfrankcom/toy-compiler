package ca.frankcom.csc435.compiler.ast;

import ca.frankcom.csc435.compiler.ast.visit.IAstVisitor;

public class SubtractExpression extends OperatorExpression {

    public SubtractExpression(Expression leftSide, Expression rightSide,
                              int lineNumber, int linePosition) {
        super(leftSide, rightSide, lineNumber, linePosition);
    }

    @Override
    public <T> T accept(IAstVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
