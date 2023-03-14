package com.lowkey.complex.designPattern;
/**
 * @author yuanjifan
 * @description Java中常用的设计模式有23种，它们分别是：
 * <p>
 * 工厂方法模式（Factory Method Pattern）
 * 抽象工厂模式（Abstract Factory Pattern）
 * 单例模式（Singleton Pattern）
 * 建造者模式（Builder Pattern）
 * 原型模式（Prototype Pattern）
 * 适配器模式（Adapter Pattern）
 * 桥接模式（Bridge Pattern）
 * 组合模式（Composite Pattern）
 * 装饰器模式（Decorator Pattern）
 * 外观模式（Facade Pattern）
 * 享元模式（Flyweight Pattern）
 * 代理模式（Proxy Pattern）
 * 责任链模式（Chain of Responsibility Pattern）
 * 命令模式（Command Pattern）
 * 解释器模式（Interpreter Pattern）
 * 迭代器模式（Iterator Pattern）
 * 中介者模式（Mediator Pattern）
 * 备忘录模式（Memento Pattern）
 * 观察者模式（Observer Pattern）
 * 状态模式（State Pattern）
 * 策略模式（Strategy Pattern）
 * 模板方法模式（Template Method Pattern）
 * 访问者模式（Visitor Pattern）
 * 这些设计模式可以帮助Java程序员更好地设计、构建和维护复杂的软件系统。
 * @date 2023/2/23 14:16
 * @param null
 * @return null
 */

/**
 * @author yuanjifan
 * @description 在这个示例中，我们定义了一个PaymentStrategy接口，其中有一个pay方法用于执行支付操作。然后，我们实现了两种不同的支付策略，即CreditCardPaymentStrategy和PayPalPaymentStrategy，它们都实现了PaymentStrategy接口。
 * <p>
 * 接下来，我们定义了一个PaymentContext类，它包含一个PaymentStrategy对象，并在构造函数中设置。PaymentContext类还有一个pay方法，用于执行支付操作。在pay方法中，我们通过调用paymentStrategy对象的pay方法来执行具体的支付操作。
 * <p>
 * 最后，我们在main方法中创建了两个PaymentContext对象，分别使用不同的支付策略，并调用pay方法来执行支付操作。
 * <p>
 * 策略模式可以使得不同的策略之间可以互相替换，而不影响系统的整体行为。这使得我们可以更加灵活地设计和实现复杂的软件系统。
 * @date 2023/2/21 8:56
 */
// 定义一个策略接口
interface PaymentStrategy {
    void pay(double amount);
}

// 实现两种不同的支付策略
class CreditCardPaymentStrategy implements PaymentStrategy {
    private final String cardNumber;
    private final String expirationDate;
    private final String cvvCode;

    public CreditCardPaymentStrategy(String cardNumber, String expirationDate, String cvvCode) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvCode = cvvCode;
    }

    public void pay(double amount) {
        // 使用信用卡支付
        System.out.println("Paid " + amount + " dollars with credit card " + cardNumber);
    }
}

class PayPalPaymentStrategy implements PaymentStrategy {
    private final String email;
    private String password;

    public PayPalPaymentStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void pay(double amount) {
        // 使用PayPal支付
        System.out.println("Paid " + amount + " dollars with PayPal account " + email);
    }
}

// 定义一个上下文类，用于执行支付操作
class PaymentContext {
    private final PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}

// 测试代码
public class StrategyPatternExample {
    public static void main(String[] args) {
        // 创建一个上下文对象，并设置使用信用卡支付策略
        PaymentContext paymentContext = new PaymentContext(new CreditCardPaymentStrategy("1234 5678 9012 3456", "12/24", "123"));
        // 执行支付操作
        paymentContext.pay(100.0);

        // 创建一个上下文对象，并设置使用PayPal支付策略
        paymentContext = new PaymentContext(new PayPalPaymentStrategy("test@example.com", "password"));
        // 执行支付操作
        paymentContext.pay(50.0);
    }
}
