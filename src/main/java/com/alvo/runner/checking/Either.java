package com.alvo.runner.checking;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a value of one of two possible types (a disjoint union.)
 * An instance of Either is either an instance of <code>left</code> or <code>right</code>.
 * e.g.: Can contain the value of type L OR R in the same time.
 *
 * @param <L> Type parameter of first(LEFT) value.
 * @param <R> Type parameter of second(RIGHT) value
 */
public abstract class Either<L, R> {

  private Either() {
  }

  /**
   * Basic projection(mapping) over the Either monad.
   * Accepts 2 functions for LEFT and RIGHT values
   * and maps the corresponding function over the presented(single existing) value (LEFT or RIGHT)
   *
   * @param leftF  function for mapping over the LEFT value.
   *               Accepts supertype for LEFT value type and returns value of type T.
   * @param rightF function for mapping over the RIGHT value
   *               Accepts supertype for RIGHT value type and returns value of type T.
   * @param <T>    desired projection type.
   * @return value of projected type T.
   */
  public abstract <T> T map(
      Function<? super L, ? extends T> leftF,
      Function<? super R, ? extends T> rightF);

  private <T> Function<T, Void> consume(Consumer<T> consumer) {
    return a -> {
      consumer.accept(a);
      return null;
    };
  }

  /**
   * Mapping application for LEFT or RIGHT value.
   *
   * @param leftF  function for projection over the LEFT value
   * @param rightF function for projection over the RIGHT value
   */
  public void apply(Consumer<? super L> leftF, Consumer<? super R> rightF) {
    map(consume(leftF), consume(rightF));
  }

  /**
   * Factory method for construction of Either container with LEFT value.
   * e.g.: the mapping on this container will be applied on LEFT value.
   *
   * @param leftValue the value to be presented in the container.
   * @param <L>       type of LEFT value
   * @param <R>       type of RIGHT value
   * @return Constructed Either container with LEFT value present.
   */
  public static <L, R> Either<L, R> left(L leftValue) {
    return new Either<L, R>() {
      @Override
      public <T> T map(Function<? super L, ? extends T> leftF,
                       Function<? super R, ? extends T> rightF) {
        return leftF.apply(Objects.requireNonNull(leftValue));
      }
    };
  }

  /**
   * Factory method for construction of Either container with RIGHT value.
   * e.g.: the mapping on this container will be applied on RIGHT value.
   *
   * @param rightValue the value to be presented in the container.
   * @param <L>        type of LEFT value
   * @param <R>        type of RIGHT value
   * @return Constructed Either container with RIGHT value present.
   */
  public static <L, R> Either<L, R> right(R rightValue) {
    return new Either<L, R>() {
      @Override
      public <T> T map(Function<? super L, ? extends T> leftF,
                       Function<? super R, ? extends T> rightF) {
        return rightF.apply(Objects.requireNonNull(rightValue));
      }
    };
  }
}