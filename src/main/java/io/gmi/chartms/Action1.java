package io.gmi.chartms;

/**
 * Created by gmead on 10/14/14.
 */
@FunctionalInterface
public interface Action1<T1> {
  public void run(T1 t1);
}
