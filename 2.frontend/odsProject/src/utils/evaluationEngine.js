/**
 * evaluationEngine.js
 * Logic for calculating ODS indicator achievement based on formulas and parameters
 * Ported and adapted from the professor's proposal to the current system.
 */

export const evaluationEngine = {
  /**
   * Evaluates a formula string replacing parameter names with their actual values.
   * Supports basic math operations: +, -, *, /, (), and decimals.
   * 
   * @param {string} formula - The mathematical formula (e.g., "(Total_A + Total_B) / 2")
   * @param {Object} parameters - Key-value pair of parameters (e.g., { Total_A: 10, Total_B: 20 })
   * @returns {number} The calculated result
   */
  evaluateFormula(formula, parameters) {
    if (!formula) return 0;
    
    try {
      // 1. Create a safe copy of the formula
      let expression = formula;
      
      // 2. Sort parameter names by length (descending) to avoid partial replacements
      // e.g., if we have 'Total' and 'Total_A', we replace 'Total_A' first.
      const paramNames = Object.keys(parameters).sort((a, b) => b.length - a.length);
      
      // 3. Replace all parameter occurrences with their values
      paramNames.forEach(name => {
        const value = parseFloat(parameters[name]) || 0;
        // Case-insensitive replacement of all occurrences
        const regex = new RegExp(this._escapeRegExp(name), 'gi');
        expression = expression.replace(regex, value);
      });
      
      // 4. Sanitize expression (only allow numbers and math operators)
      // This prevents basic injection attacks
      expression = expression.replace(/[^0-9+\-*/(). ]/g, '');
      
      // 5. Evaluate the math expression
      // We use Function constructor which is slightly safer than eval when sanitized
      // and much faster than building a full parser
      const result = new Function(`return ${expression}`)();
      
      return isFinite(result) ? result : 0;
    } catch (error) {
      console.error('Error evaluating formula:', formula, error);
      return 0;
    }
  },

  /**
   * Calculates achievement percentage relative to a goal.
   * 
   * @param {number} value - The current calculated value
   * @param {Object} goal - The goal object { value, unit }
   * @returns {number} Percentage (0-100+)
   */
  calculateAchievement(value, goal) {
    if (!goal || !goal.value) return 0;
    const goalVal = parseFloat(goal.value);
    
    // For many ODS indicators, lower is better (e.g., poverty, emissions)
    // Here we assume higher is better, but we could add a direction flag to the goal
    const achievement = (value / goalVal) * 100;
    
    return isFinite(achievement) ? achievement : 0;
  },

  /**
   * Helper to escape special regex characters
   */
  _escapeRegExp(string) {
    return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
  }
};
