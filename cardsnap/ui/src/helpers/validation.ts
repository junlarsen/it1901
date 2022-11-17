/**
 * Function to validate a deckname. Corresponding to the validation in the backend.
 * @param newName string name of deck
 * @returns boolean true if value is valid, false if not
 */
export const validateDeckName = (newName: string): boolean => {
  if (!newName || !newName.trim()) return false;
  if (newName.length < 3) return false;
  if (newName.length > 32) return false;
  return true;
};

/**
 * Functio to validate a question. Corresponding with the validation in backend.
 * @param newQuestion string question to be validated
 * @returns boolean true if valid, otherwise false
 */
export const validateQuestion = (newQuestion: string): boolean => {
  if (newQuestion.length === 0) return false;
  return true;
};

/**
 * Functio to validate a answer. Corresponding with the validation in backend.
 * @param newAnswer string answer to be validated
 * @returns boolean true if valid, otherwise false
 */
export const validateAnswer = (newAnswer: string): boolean => {
  if (newAnswer.length === 0) return false;
  return true;
};
