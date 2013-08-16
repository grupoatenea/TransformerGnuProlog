package co.edu.eafit.tvl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import co.edu.eafit.tvl.transformation.TVLToGNUPrologTransformer;
import co.edu.eafit.tvl.transformation.TVLToGNUPrologTransformerImpl;

public class TVLToGNUPrologTransformerTest {

	@Test
	public void testTransform() throws IOException {
		File f = new File("/TVL/test5LongID.pl");
		f.delete();
		TVLToGNUPrologTransformer transformer = new TVLToGNUPrologTransformerImpl("/TVL/test5LongID.tvl", "/TVL/test5LongID.pl");
		transformer.transform();
		assertTrue(f.exists());
	}
	
	@Test
	public void testTransformEnumValues() throws IOException {
		File f = new File("/TVL/test1EnumBooleanForm.pl");
		f.delete();
		TVLToGNUPrologTransformer transformer = new TVLToGNUPrologTransformerImpl("/TVL/test1EnumBooleanForm.tvl", "/TVL/test1EnumBooleanForm.pl");
		transformer.transform();
		assertTrue(f.exists());
	}
	
	@Test
	public void testTransformIntValues() throws IOException {
		File f = new File("/TVL/testDeportivos.pl");
		f.delete();
		TVLToGNUPrologTransformer transformer = new TVLToGNUPrologTransformerImpl("/TVL/testDeportivos.tvl", "/TVL/testDeportivos.pl");
		transformer.transform();
		assertTrue(f.exists());
	}
	
	@Test
	public void testTransformComplexTVL() throws IOException {
		File f = new File("/TVL/rexel_v4.pl");
		f.delete();
		TVLToGNUPrologTransformer transformer = new TVLToGNUPrologTransformerImpl("/TVL/rexel_v4.tvl", "/TVL/rexel_v4.pl");
		transformer.transform();
		assertTrue(f.exists());
	}
	
	@Test
	public void testTransformBooleanValue() throws IOException {
		File f = new File("/TVL/testBus.pl");
		f.delete();
		TVLToGNUPrologTransformer transformer = new TVLToGNUPrologTransformerImpl("/TVL/testBus.tvl", "/TVL/testBus.pl");
		transformer.transform();
		assertTrue(f.exists());
	}
	
	@Test
	public void testTransformSisgeo() throws IOException {
		File f = new File("/TVL/sisgeo.pl");
		f.delete();
		TVLToGNUPrologTransformer transformer = new TVLToGNUPrologTransformerImpl("/TVL/sisgeo.tvl", "/TVL/sisgeo.pl");
		transformer.transform();
		assertTrue(f.exists());
	}

}
