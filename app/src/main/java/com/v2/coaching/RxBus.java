package com.v2.coaching;


import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;


/**
 * Created by CaioSChristino on 14/09/17.
 */
public class RxBus {
    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        _bus.onNext(o);
    }

//    public <T> Subscription register(final Class<T> eventClass, Action1<T> onNext) {
//        return _bus
//                .observeOn(AndroidSchedulers.mainThread())
//                .filter(event -> event.getClass().equals(eventClass))
//                .map(obj -> (T) obj)
//                .subscribe(onNext);
//    }
//
//    public Observable<Object> toObservable() {
//        return _bus;
//    }
}

