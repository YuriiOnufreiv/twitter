package ua.onufreiv.twitter.infrastructure;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class InitialContext {
    private Config config;

    public InitialContext(Config config) {
        this.config = config;
    }

    public Object lookup(String name) throws Exception {
        Class<?> impl = config.getImpl(name);
        return impl.newInstance();
    }
}
