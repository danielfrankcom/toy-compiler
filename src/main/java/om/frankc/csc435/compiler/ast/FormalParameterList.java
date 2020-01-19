package om.frankc.csc435.compiler.ast;

import java.util.List;

public class FormalParameterList extends NodeList<FormalParameter> {

    public FormalParameterList(List<FormalParameter> parameters) {
        super(parameters);
    }

    @Override
    public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }

}
