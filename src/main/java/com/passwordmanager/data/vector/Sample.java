


// Generic class example to show how to setup generic classes type for base configuration type
public class BaseConfigurationType<T> {
    // an object of type T is declared
    private T type;

    // constructor
    public BaseConfigurationType(T type){
        this.type = type;
    }

    public BaseConfigurationType() {

    }

    // getter method
    public T getObject() {
        return type;
    }
    // setter method
    public void setObject(T type) {
        this.type = type;
    }
}


// Use of generic class
public void setDarkMode(){;
    BaseConfigurationType<Boolean> darkMode = new BaseConfigurationType<>(false);

    darkMode.setObject(true);

    if (darkMode.getObject()) {
        // do something
    }
}
