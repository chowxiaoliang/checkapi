package thread.threadlocal;

/**
 * @author zhouliang
 * Hiberante 中使用threadlocal对session进行管理
 */
public class ThreadLocalInSession {

    private SessionFactory sessionFactory = new SessionFactory();

    private static final ThreadLocal<Session> THREAD_LOACL = new ThreadLocal<>();

    /**
     * 获取当前线程的session
     * @return
     */
    private Session getCurrentSession(){
        Session session = THREAD_LOACL.get();
        if(session == null){
            session = sessionFactory.openSession();
            THREAD_LOACL.set(session);
        }
        return session;
    }

    /**
     * 关闭session
     */
    private void closeSession(){
        Session session = THREAD_LOACL.get();
        if (session != null){
            THREAD_LOACL.remove();
        }
    }

    class SessionFactory {

        public Session openSession(){
            return new Session();
        }
    }

    private class Session {

        private String certNo;

        private String name;

        private String phone;
    }


}
