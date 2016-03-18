/*******************************************************************************
 * Copyright (c) 2009, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ian Phillips - initial implementation.
 *******************************************************************************/
package org.eclipse.core.internal.expressions.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.internal.expressions.CountExpression;

import org.eclipse.core.runtime.CoreException;

public class CountExpressionTest extends TestCase {
	public static Test suite() {
		return new TestSuite(CountExpressionTest.class);
	}

	private static EvaluationContext evaluationContext(int size) {
		List<Integer> variable = new ArrayList<>(size + 1);
		for (int i = 0; i < size; ++i)
			variable.add(Integer.valueOf(i));
		return new EvaluationContext(null, variable);
	}

	public void testNoneExpression() throws CoreException {
		CountExpression e = new CountExpression("!"); //$NON-NLS-1$
		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(0)));
		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(1)));
	}

	public void testNoneOrOneExpression() throws CoreException {
		CountExpression e = new CountExpression("?"); //$NON-NLS-1$
		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(0)));
		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(1)));
		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(2)));
	}

	public void testExactExpression() throws CoreException {
		CountExpression e = new CountExpression("5"); //$NON-NLS-1$
		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(5)));
		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(7)));
	}

	public void testAnyNumberExpression() throws CoreException {
		CountExpression e = new CountExpression("*"); //$NON-NLS-1$
		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(5)));
	}

//	@Test
//	public void testLessThanOrEqualToExpression() throws CoreException {
//		CountExpression e = new CountExpression("-3]"); //$NON-NLS-1$
//		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(1)));
//		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(3)));
//		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(4)));
//	}

	public void testLessThanExpression() throws CoreException {
		CountExpression e = new CountExpression("-3)"); //$NON-NLS-1$
		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(1)));
		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(3)));
		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(4)));
	}

//	@Test
//	public void testGreaterThanOrEqualToExpression() throws CoreException {
//		CountExpression e = new CountExpression("[3-"); //$NON-NLS-1$
//		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(5)));
//		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(3)));
//		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(2)));
//	}

	public void testGreaterThanExpression() throws CoreException {
		CountExpression e = new CountExpression("(3-"); //$NON-NLS-1$
		Assert.assertEquals(EvaluationResult.TRUE, e.evaluate(evaluationContext(5)));
		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(3)));
		Assert.assertEquals(EvaluationResult.FALSE, e.evaluate(evaluationContext(2)));
	}

}
