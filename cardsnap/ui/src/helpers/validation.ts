export const validateDeckName = (newName: string): boolean => {
  if (!newName || !newName.trim()) return false;
  if (newName.length < 3) return false;
  if (newName.length > 32) return false;
  return true;
};
