package net.uncrash.authorization.listener.event;

import net.uncrash.authorization.User;
import org.springframework.context.ApplicationEvent;

/**
 * 注册用户成功事件
 *
 * @Author Sendya
 */
public class RegisterSuccessEvent extends ApplicationEvent {

    private User user;

    public RegisterSuccessEvent(User user) {
        super(user.getUsername());
    }

    public User getUser() {
        return user;
    }
}
