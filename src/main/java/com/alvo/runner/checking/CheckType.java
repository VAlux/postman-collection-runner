package com.alvo.runner.checking;

/**
 * <ul>
 *  <li>CONFORMITY: Checks if the requested model <B>content</B>  conforms to the responded models <B>content</B>.</li>
 *  <li>CONSISTENCY: Checks the availability of the data.</li>
 *  <li>SECURITY: Checks for the security vulnerabilities.</li>
 *  <li>PERFORMANCE: Checks the performance metrics and expectations.</li>
 * </ul>
 */
public enum CheckType {
  CONFORMITY,
  CONSISTENCY,
  SECURITY,
  PERFORMANCE,
  UNKNOWN
}
