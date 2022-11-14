export const validateDeckName = (newName: string): boolean => {
  if (!newName || !newName.trim()) return false;
  if (newName.length < 3) return false;
  if (newName.length > 32) return false;
  return true;
};

export const validateQuestion = (newQuestion: string): boolean => {
  if (newQuestion.length === 0) return false;
  return true;
};

export const validateAnswer = (newAnswer: string): boolean => {
  if (newAnswer.length === 0) return false;
  return true;
};
