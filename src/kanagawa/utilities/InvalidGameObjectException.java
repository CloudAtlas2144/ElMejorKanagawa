package kanagawa.utilities;

/**
 * Class extending the Exception class, used to throw an exception when the
 * parsing of an object failed
 */
public class InvalidGameObjectException extends Exception {

    private Object parent;

    private Object object;

    public InvalidGameObjectException(Object object) {
        this.object = object;
        this.parent = null;
    }

    public InvalidGameObjectException(Object object, Object parent) {
        this.object = object;
        this.parent = parent;
    }

    @Override
    public void printStackTrace() {
        if (this.parent == null) {
            System.err.println(object.toString() + " is invalid");
        } else {
            System.err.println(object.toString() + " in " + parent.toString() + " is invalid.");
        }
    }

    public Object getParent() {
        return parent;
    }

    public Object getObject() {
        return object;
    }
}
