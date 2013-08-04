package co.edu.eafit.tvl.expression;

public class GNUPrologFalseExpression implements GNUPrologExpression {

	@Override
	public String toArithmeticForm() {
		return "0";
	}

}
