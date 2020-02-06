package com.github.dge1992.commonserver.intercepter;

import com.github.dge1992.common.domain.User;

public class UserContextHolder {
	public static ThreadLocal<User> context = new ThreadLocal();
	public static User currentUser() {
		return context.get();
	}
	public static void set(User user) {
		context.set(user);
	}
	public static void remove() {
		context.remove();
	}
}
