package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティ設定を無視するリクエスト設定
		// 静的リソースに対するアクセスはセキュリティ設定を無視する
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//アクセス制限
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll() //制限なしURL
				.antMatchers("/admin").hasAuthority("ADMIN") //特定ロールのみアクセス可能なURL
				.anyRequest().authenticated() //認証されたリクエストのみ許可
				.and()
				//認証前に認証がいるページにアクセスされた場合のリダイレクト先
			.formLogin()
				.loginPage("/login") //ログイン画面URL
				.permitAll() //アクセス制限なし
				.and()
			.logout()
				.logoutUrl("/logoutURL") //ログアウトする場合のURL
				.logoutSuccessUrl("/login") //ログアウト成功した場合に飛ばす先
//				.invalidateHttpSession(true) //セッション破棄
				.permitAll(); //アクセス制限なし
//				.and()
//			.sessionManagement()
//				.invalidSessionUrl("/invalidSession");
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		// メモリに認証情報を格納
		auth
				.inMemoryAuthentication()
				.withUser(
						User.withDefaultPasswordEncoder()
								.username("user")
								.password("password")
								.roles("USER")
								.build())
				.and()
				.inMemoryAuthentication()
				.withUser(
						User.withDefaultPasswordEncoder()
								.username("admin")
								.password("admin")
								.roles("ADMIN", "USER")
								.build());
		// DBから認証情報取得
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.usersByUsernameQuery(
//						"select mail_address as username, password, enabled from accounts where mail_address = ?")
//				.authoritiesByUsernameQuery(
//						"select mail_address, role from accounts where mail_address = ?")
//				.passwordEncoder(new ShaPasswordEncoder(256));
	}
}
