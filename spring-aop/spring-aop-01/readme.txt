1,测试方式：junit测试，运行SpringAop01ApplicationTests 的 testAspectLog001方法
2，spring aop项目介绍
    2.1 IDE为 idea集成开发环境，基于maven构建
    2.2 采用springboot测试开发
    2,3 创建好springboot的环境后，在pom.xml中增加

        <!--aop-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
	2.4 切面类LogAspectJ 用注解@Aspect修身，并且必须为加入到spring IOC，所以索要用@Component修饰

    通知方式
        @Before(value = "execution(public int com.tomatoman.springaop01.CalculatorImpl.*(int,int))")
        public void beforeMethod(JoinPoint joinPoint) {
            String method = joinPoint.getSignature().getName();
            List list = Arrays.asList(joinPoint.getArgs());
            System.out.println("aop beforeMethod: method is " + method + " args is " + list );
        }

        其中：
        public int 为方法修饰，表示拦截public int 修饰的方法
        com.tomatoman.springaop01 包名
        CalculatorImpl 类名
        com.tomatoman.springaop01.CalculatorImpl.* 拦截对应包名下类CalculatorImpl的所有有两个int,int 类型参数的方法


        其中CalculatorImpl需要进入到spring IOC容器用@Component修饰

        @Component
        public class CalculatorImpl implements Calculator{
            public int add(int a, int b) {
                int result = a + b;
                System.out.println("add result = " + result);
                return result;
            }
