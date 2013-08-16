package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.BooleanExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.EqualsExpression;

public class GNUPrologEqualsExpression implements GNUPrologExpression {

	private EqualsExpression expression;

	public GNUPrologEqualsExpression(EqualsExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		String gnuPrologLeftExpression = GNUPrologExpressionTransformer.transform( expression.getExpression1() ).toArithmeticForm(); 
		String gnuPrologRightExpression = GNUPrologExpressionTransformer.transform( expression.getExpression2() ).toArithmeticForm();
		String gnuEqualsExpression = gnuPrologLeftExpression + " #= " + gnuPrologRightExpression;
//		if ( expression.getExpression2() instanceof IntExpression){
//			
//		}
		if ( expression.getExpression2() instanceof BooleanExpression) {
			gnuEqualsExpression = gnuPrologLeftExpression + " #==> " + gnuPrologRightExpression;
		} else {
			gnuEqualsExpression = gnuPrologLeftExpression + " #> 0 #==> " + gnuEqualsExpression;
		}
		return gnuEqualsExpression;
	}

}
