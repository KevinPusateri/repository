package MVC;
/* Generated by Together */

public interface ChatObservable {
    void attach(ChatObserver forumObserver);

    void detach(ChatObserver forumObserver);

    void inform();
}