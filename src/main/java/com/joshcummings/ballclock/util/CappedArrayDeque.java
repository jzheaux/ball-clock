package com.joshcummings.ballclock.util;

import java.util.ArrayDeque;

public class CappedArrayDeque<E> extends ArrayDeque<E> {
    int max;
    
    public CappedArrayDeque(int max) {
        this.max = max;
    }
    
    @Override
    public boolean offer(E e) {
        if ( size() == max ) {
            return false;
        }
        return super.offer(e);
    }
    
    @Override
    public boolean offerLast(E e) {
        if ( size() == max ) {
            return false;
        }
        return super.offerLast(e);
    }
}
