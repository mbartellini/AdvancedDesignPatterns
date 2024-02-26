# Setup

![image](https://github.com/mbartellini/AdvancedDesignPatterns/assets/67906197/603e54b0-10c8-4c28-9fbe-2bb9ca21851a)


# Brief description
This application addresses the challenge of managing a restaurant where support for many languages may be necessary. It allows users to view a menu consisting of various dishes and select their preferred language for translation. With integration for translation platforms, owners can translate the menu into a variety of languages.

# Design Patterns

**Builder**: We use the Builder pattern in order to initialize an object with Dishes and Language, this enables us to make Menu more dynamic during initialization with different languages or the creation without sides is also possible. This makes the code more readable because the setter methods are in the Builder class

**Chain of responsibility**: For exception and error handling, we create a chain of responsibility. Each handler checks if they can create a valid response given the exception type, and pass it on otherwise.

**Singleton**: We instantiate the various MenuExceptionHandlers as Singleton inside the GlobalExceptionHandler class, since the management of the chain of responsibility does not need to be instantiated more than once. Also, the classes annotated with Autowired are Singletons managed by Spring Boot.

# Reflection
We had other design patterns in mind that we tried like singleton and facade, but we realized that spring boot already has a better and more commonly used implementation thus we didn’t want to reinvent the wheel just to use a design pattern.
The Builder pattern sounded more promising than it is actually in use at the moment, for it to become more useful we would need more attribute possibilities or types, for example by dividing food menus vs drink menus, then the Builder design pattern would be more useful. It would help with the maintainability of the code, as it provides a clean way of adding new attributes to the class without a need of changing a commonly used unique constructor.
The singleton pattern allowed us to force minimal runtime memory usage as having several instances of some classes did not provide any improvements. We implemented them ourselves but they are also widely used in the spring boot framework we used.
The chain of responsibility gave us an easy and maintainable way to handle different error types, as we only have to create a new subclass of the MenuHandlerException and add it to the linked list.
We had also designed this architecture with the strategy pattern in mind, thinking the program should allow the user to use different translation APIs. However, we could not find more than one free translation API in only one week. This is why we have a CustomTranslator interface but only one class that implements it.

