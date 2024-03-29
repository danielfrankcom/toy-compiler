package ca.frankcom.csc435.compiler.jasmin.visit;

import ca.frankcom.csc435.compiler.jasmin.*;

public interface IJasminVisitor<T> {

    T visit(JProgram program);

    T visit(JFunction function);

    T visit(FunctionDeclaration declaration);

    T visit(LimitDirectives directives);

    T visit(VarDirective directive);

    T visit(JLabel label);

    T visit(JLoad instruction);

    T visit(JStore instruction);

    T visit(JDuplicateTopOfStack instruction);

    T visit(JIfZero instruction);

    T visit(JIfLessThanZero instruction);

    T visit(JIfGreaterThanZero instruction);

    T visit(JGoTo instruction);

    T visit(ValueReturn instruction);

    T visit(VoidReturn instruction);

    T visit(JInvokeInstruction instruction);

    T visit(JLoadPrintStream instruction);

    T visit(JStringBuilderNew instruction);

    T visit(JStringBuilderInit instruction);

    T visit(JStringBuilderAppend instruction);

    T visit(JStringBuilderOutput instruction);

    T visit(JStringCompare instruction);

    T visit(JArrayNew instruction);

    T visit(JArrayStore instruction);

    T visit(JArrayLoad instruction);

    T visit(JPrint instruction);

    T visit(JPrintLn instruction);

    T visit(JLoadConstant instruction);

    T visit(IntegerConstant constant);

    T visit(BooleanConstant constant);

    T visit(FloatConstant constant);

    T visit(CharConstant constant);

    T visit(StringConstant constant);

    T visit(JFloatCompare instruction);

    T visit(JFloatSubtract instruction);

    T visit(JFloatAdd instruction);

    T visit(JFloatMultiply instruction);

    T visit(JIntegerSubtract instruction);

    T visit(JIntegerAdd instruction);

    T visit(JIntegerMultiply instruction);

    T visit(JIntegerXor instruction);

}
