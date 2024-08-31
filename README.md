JSON Proxy Library
==================

> [!NOTE]
> This project is under construction and not ready for use.
> I work on this project during my free time only.
> There is no guarantee that I will ever finish this project since it is
> competing with various other projects for my time.

JSON object mapper for Java using interfaces instead of POJOs.

```java
/**
 * A product from a catalog.
 */
@JsonInterface
public interface Product {

    /**
     * The unique identifier for the product (UUID).
     *
     * @example "12aeb80b-9c5d-417e-854b-739ec861ae49"
     */
    UUID id();

    /**
     * Name of the product.
     *
     * @example "Example Product"
     */
    String name();

    /**
     * The price of the product in EUR cents.
     *
     * @example 1999
     */
    int price();

    /**
     * Tags associated with the product.
     *
     * @example ["electronics", "sale"]
     */
    Set<String> tags();

    /**
     * The dimensions of the product (optional).
     */
    @Nullable
    Dimensions dimensions();
}

@JsonInterface
public interface Dimensions {

    /**
     * @example 10.0
     */
    double length();

    /**
     * @example 5.0
     */
    double width();

    /**
     * @example 2.0
     */
    double height();
}
```

Rationale
---------

### Less boilerplate

Using interfaces instead of POJOs allows to avoid a lot of boilerplate in Java.
You only need to declare one method declaration, instead of having to declare a
field, a getter, and either a constructor parameter or a setter.

### Easier to read

The reduced amount of boilerplate also reduces the noise and therefore makes it
easier to understand the resulting JSON schema while reading the sources. As a
result, it is easier to write high-quality APIs.

### Separation of concerns

By using interfaces, you separate your internal data classes form how they are
represented on the wire. I suggest using anonymous classes to convert your
internal data into JSON.

```java
@GET
public Product get(@PathParam("id") UUID id) {
    var product = getProductInternal(id);
    return new Product() {
        @Override
        public UUID id() {
            return product.getId();
        }

        @Override
        public String name() {
            return product.getName();
        }

        ...
    };
}
```

### Lazy loading of content

Using interfaces and proxies provide great flexibility without having to touch
the declarations of the JSON types. For example, by only deserializing the
content of the JSON document which is actually accessed, you can save a lot of
CPU time when only accessing a small portion of a very large JSON document. Note
that we will probably still have to load the whole document into memory.
