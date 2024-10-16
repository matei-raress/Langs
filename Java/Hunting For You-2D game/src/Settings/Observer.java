package Settings;

/*! \class public abstract class Observer
    \brief folosit pentru a implementa sablonul Observer
 */
public abstract class Observer {
    protected Settings subject;

    public abstract void update();
}
