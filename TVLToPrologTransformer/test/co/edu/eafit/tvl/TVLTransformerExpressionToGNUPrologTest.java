package co.edu.eafit.tvl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import be.ac.info.fundp.TVLParser.SyntaxTree.AbsExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AndAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AndExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AvgAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.DivideExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.EqualsExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExcludesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExpressionList;
import be.ac.info.fundp.TVLParser.SyntaxTree.GEQExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.GreaterExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IfAndOnlyIfExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ImpliesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IncludesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IntExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LEQExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LowerExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.NotExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.OrExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.PlusExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.QuestExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.RealExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ZeroExpression;
import co.edu.eafit.tvl.expression.GNUPrologTransformer;

public class TVLTransformerExpressionToGNUPrologTest {

	@Test
	public void testAbsPlusRealExpression() {
		RealExpression s1 = new RealExpression("2.6");
		RealExpression s2 = new RealExpression("2.9");
		PlusExpression plusExpression = new PlusExpression(s1, s2);
		AbsExpression absExpression = new AbsExpression(plusExpression);
		String gnuAbsExpressionString = GNUPrologTransformer.transform(absExpression).toArithmeticForm();
		assertEquals ("abs(2.6 + 2.9)", gnuAbsExpressionString);
	}
	
	@Test
	public void testAndAggExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		ExpressionList expressionList = new ExpressionList(intExpression1, new ExpressionList(intExpression2));
		AndAggExpression andAggExpression = new AndAggExpression(expressionList);
		String gnuExpressionString = GNUPrologTransformer.transform(andAggExpression).toArithmeticForm();
		assertEquals ("5,\n123", gnuExpressionString);
	}
	
	@Test
	public void testAndExpression() {
		LowerExpression lowerExpression = new LowerExpression(new IntExpression("123"), new IntExpression("5"));
		GreaterExpression greaterExpression = new GreaterExpression(new RealExpression("123.5"), new RealExpression("5.8"));
		AndExpression andExpression = new AndExpression(lowerExpression, greaterExpression);
		String gnuExpressionString = GNUPrologTransformer.transform(andExpression).toArithmeticForm();
		assertEquals ("123 #< 5 #/\\ 123.5 #> 5.8", gnuExpressionString);
	}
	
	@Test
	public void testAvgExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		ExpressionList expressionList = new ExpressionList(intExpression1, new ExpressionList(intExpression2));
		AvgAggExpression avgAggExpression = new AvgAggExpression(expressionList);
		String gnuExpressionString = GNUPrologTransformer.transform(avgAggExpression).toArithmeticForm();
		assertEquals ("(5 + 123) / 2", gnuExpressionString);
	}
	
	@Test
	public void testEqualsExpression() {
		GEQExpression geqExpression = new GEQExpression(new LongIDExpression("Car.hp", null), new IntExpression("123"));
		LEQExpression leqExpression = new LEQExpression(new LongIDExpression("Bus.hp", null), new IntExpression("800"));
		EqualsExpression equalsExpression = new EqualsExpression(geqExpression, leqExpression);
		String gnuExpressionString = GNUPrologTransformer.transform(equalsExpression).toArithmeticForm();
		assertEquals ("Car.hp #>= 123 #= Bus.hp #=< 800", gnuExpressionString);
	}
	
	@Test
	public void testExcludesExpression() {
		LongIDExpression longIDExpresion1 = new LongIDExpression("Car.hp", null);
		LongIDExpression longIDExpresion2 = new LongIDExpression("Bus.hp", null);
		ExcludesExpression excludesExpression = new ExcludesExpression(longIDExpresion1.getLongID(), longIDExpresion2.getLongID(), null);
		String gnuExpressionString = GNUPrologTransformer.transform(excludesExpression).toArithmeticForm();
		assertEquals ("Car.hp + Bus.hp #=< 1", gnuExpressionString);
	}
	
	@Test
	public void testIncludesExpression() {
		LongIDExpression longIDExpresion1 = new LongIDExpression("Car.hp", null);
		LongIDExpression longIDExpresion2 = new LongIDExpression("Bus.hp", null);
		IncludesExpression includesExpression = new IncludesExpression(longIDExpresion1.getLongID(), longIDExpresion2.getLongID(), null);
		String gnuExpressionString = GNUPrologTransformer.transform(includesExpression).toArithmeticForm();
		assertEquals ("Car.hp #==> Bus.hp", gnuExpressionString);
	}
	
	@Test
	public void testImpliesExpression() {
		LongIDExpression longIDExpresion1 = new LongIDExpression("Car", null);
		LongIDExpression longIDExpresion2 = new LongIDExpression("Bus", null);
		ImpliesExpression impliesExpression = new ImpliesExpression(longIDExpresion1, longIDExpresion2);
		String gnuExpressionString = GNUPrologTransformer.transform(impliesExpression).toArithmeticForm();
		assertEquals ("Car #==> Bus", gnuExpressionString);
	}
	
	@Test
	public void testQuestExpression() {
		GEQExpression expression1 = new GEQExpression(new LongIDExpression("Car", null), new IntExpression("123"));
		LEQExpression expression2 = new LEQExpression(new LongIDExpression("Bus", null), new IntExpression("800"));
		GreaterExpression expression3 = new GreaterExpression(new RealExpression("123.5"), new RealExpression("5.8"));
		QuestExpression questExpression = new QuestExpression(expression1, expression2, expression3);
		String gnuExpressionString = GNUPrologTransformer.transform(questExpression).toArithmeticForm();
		assertEquals ("( Car #>= 123 ) #==> ( Bus #=< 800 ), ( #\\ Car #>= 123 ) #==> ( 123.5 #> 5.8 )", gnuExpressionString);
	}
	
	@Test
	public void testZeroExpression() {
		ZeroExpression zeroExpression = new ZeroExpression();
		String gnuExpressionString = GNUPrologTransformer.transform(zeroExpression).toArithmeticForm();
		assertEquals ("0", gnuExpressionString);
	}

	@Test
	public void testOrExpression() {
		LowerExpression lowerExpression = new LowerExpression(new IntExpression("123"), new IntExpression("5"));
		GreaterExpression greaterExpression = new GreaterExpression(new RealExpression("123.5"), new RealExpression("5.8"));
		OrExpression andExpression = new OrExpression(lowerExpression, greaterExpression);
		String gnuExpressionString = GNUPrologTransformer.transform(andExpression).toArithmeticForm();
		assertEquals ("123 #< 5 #\\/ 123.5 #> 5.8", gnuExpressionString);
	}
	
	@Test
	public void testNotExpression() {
		NotExpression notExpression = new NotExpression(new LongIDExpression("Car", null));
		String gnuExpressionString = GNUPrologTransformer.transform(notExpression).toArithmeticForm();
		assertEquals ("#\\ Car", gnuExpressionString);
	}
	

	@Test
	public void testDivideExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		RealExpression expression2 = new RealExpression("123.5");
		DivideExpression divideExpression = new DivideExpression(expression1, expression2);
		String gnuExpressionString = GNUPrologTransformer.transform(divideExpression).toArithmeticForm();
		assertEquals ("Car.hp / 123.5", gnuExpressionString);
	}
	
	@Test
	public void testIfAndOnlyIfExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		RealExpression expression2 = new RealExpression("123.5");
		IfAndOnlyIfExpression ifAndOnlyIfExpression = new IfAndOnlyIfExpression(expression1, expression2);
		String gnuExpressionString = GNUPrologTransformer.transform(ifAndOnlyIfExpression).toArithmeticForm();
		assertEquals ("Car.hp #<=> 123.5", gnuExpressionString);
	}

}
