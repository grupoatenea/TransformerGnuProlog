package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.InExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.SetExpression;

public class GNUPrologInExpression implements GNUPrologExpression {

	private InExpression inExpression;

	public GNUPrologInExpression(InExpression expression) {
		this.inExpression = expression;
	}

	@Override
	public String toArithmeticForm() {
		
		Expression expression = inExpression.getExpression();
		SetExpression setExpression = inExpression.getSetExpression();
		String gnuPrologExpression = GNUPrologExpressionTransformer.transform(expression).toArithmeticForm();
		
		if (setExpression.hasAnExpressionList()){
			Vector<Expression> expressions = setExpression.getExpressionList().getExpressions();
			String list = "(" + gnuPrologExpression + " #= " + GNUPrologExpressionTransformer.transform(expressions.get(0)).toArithmeticForm();
			int i = 1;
			while ( i <= expressions.size()-1) {
				list = list.concat("; " + gnuPrologExpression + " #= " + GNUPrologExpressionTransformer.transform(expressions.get(i)).toArithmeticForm());
				i++;
			
			}
			return list + ")";
		} 
		return gnuPrologExpression + " #>= " + setExpression.getMinExpression() + ", " + gnuPrologExpression + " #=< " + setExpression.getMaxExpression();
	}

}
