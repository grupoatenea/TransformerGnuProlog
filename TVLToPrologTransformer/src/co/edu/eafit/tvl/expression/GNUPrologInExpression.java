package co.edu.eafit.tvl.expression;

import java.util.List;
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
		String gnuPrologExpression = GNUPrologTransformer.transform(expression).toArithmeticForm();
		
		if (setExpression.hasAnExpressionList()){
			Vector<Expression> expressions = setExpression.getExpressionList().getExpressions();
			String list = gnuPrologExpression + " #= " + GNUPrologTransformer.transform(expressions.get(0)).toArithmeticForm();
			int i = 1;
			while ( i <= expressions.size()-1) {
				list = list.concat("; " + gnuPrologExpression + " #= " + GNUPrologTransformer.transform(expressions.get(i)).toArithmeticForm());
				i++;
			
			}
			return list;
		} 
		return gnuPrologExpression + " #>= " + setExpression.getMinExpression() + ", " + gnuPrologExpression + " #=< " + setExpression.getMaxExpression();
	}

}
