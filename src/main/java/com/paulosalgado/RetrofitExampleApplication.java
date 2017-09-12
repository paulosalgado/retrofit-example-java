package com.paulosalgado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitExampleApplication {
	
	public static void main(String[] args) {
		
		UserService service = GitHubServiceGenerator.createService(UserService.class);
		
		synchronousServiceCall(service);
		asynchronousServiceCall(service);
	}
	
	private static void synchronousServiceCall(UserService service) {
		
		Call<User> callSync = service.getUser("paulosalgado");
		
		try {
			Response<User> response = callSync.execute();
			User user = response.body();
			System.out.println("synchronousServiceCall >> " + user.toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void asynchronousServiceCall(UserService service) {
		
		Call<User> callAsync = service.getUser("paulosalgado");
		
		callAsync.enqueue(new Callback<User>() {
			
			@Override
			public void onResponse(Call<User> call, Response<User> response) {
				User user = response.body();
				System.out.println("asynchronousServiceCall >> " + user.toString());
			}
			
			@Override
			public void onFailure(Call<User> call, Throwable throwable) {
				System.out.println(throwable);
			}
			
		});
	}
	
}
