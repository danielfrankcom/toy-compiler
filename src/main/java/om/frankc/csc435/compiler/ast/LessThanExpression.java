package om.frankc.csc435.compiler.ast;

public class LessThanExpression extends OperatorExpression {

    public LessThanExpression(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }

}
