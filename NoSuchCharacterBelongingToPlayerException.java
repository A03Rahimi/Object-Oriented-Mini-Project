
// Exception to throw when the given character (to be removed) is not found in the list of characters
// inside the Player object
class NoSuchCharacterBelongingToPlayerException extends Exception {
    public NoSuchCharacterBelongingToPlayerException(String name) {
        super(name);
    }
}