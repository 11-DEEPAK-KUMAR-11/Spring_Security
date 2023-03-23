# Spring_Security
## Spring boot applications with spring security

**Introduction**

Spring Security provides comprehensive security services for J2EE-based enterprise software
applications.

Spring Security is a framework that provides various security features like authentication, and authorization to create secure Java Enterprise Applications.

This framework targets two major areas of application - authentication and authorization.

**Authentication**

Authentication is how we verify the identity of who is trying to access a particular resource. A common way to authenticate users is by requiring the user to enter a username and password. Once authentication is performed we know the identity and can perform authorization. Http response code in case of failed authentication is 401.



**Authorization**

Authorization is the process to allow the authority to perform actions in the application. We can apply authorization to authorize web requests, methods, and access to the individual domain. Http response code in case of failed authorization is 403. 



### Spring Security Dependency for Maven

```java
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

# Spring Security Components



### Spring Security Filters

A series of Spring Security filters intercept each request & work together to identify if Authentication is required or not. If Authentication is required, accordingly navigate the user to the login page or use the existing details stored during initial authentication.

### Authentication

Filters like UsernamePasswordAuthenticationFilter will extract username/password from HTTP request & prepare Authentication type object. Because Authentication is the core standard of storing authenticated user details inside the Spring Security framework.

### AuthenticationManager

Once received a request from the filter, it delegates the validation of the user details to the authentication providers available. Since there can be multiple providers inside an app, it is the responsibility of the Authentication Manager to manage all the authentication providers available.

### AuthenticationProvider

Authentication Providers have all the core logic of validating user details for authentication.

### UserDetailsManager/UserDetailsService

UserDetailsManager/UserDetailsService helps in retrieving, creating, updating, and deleting the User Details from the DB/storage systems. It is one of the core interfaces of Spring Security. The authentication of any request mostly depends on the implementation of the UserDetailsService interface. It is most commonly used in database-backed authentication to retrieve user data. The data is retrieved with the implementation of the lone loadUserByUsername() method where we can provide our logic to fetch the user details for a user. The method will throw a UsernameNotFoundException if the user is not found.

### PasswordEncoder

Service interface that helps in encoding & hashing passwords. Otherwise, we may have to live with plain text passwords.

### SecurityContext

Once the request has been authenticated, the Authentication will usually be stored in a thread-local SecurityContext managed by the SecurityContextHolder. This helps during the upcoming requests from the same user.

# Spring Security Flow

1)User trying to access a secure page for the first time

2)Behind the scenes few filters like AbstractSecurityInterceptor, DefaultLoginPageGeneratingFilter

  identify that the user is not logged in & redirect the user to the login page

3)User entered his credentials and the request is intercepted by filters

4)Filters like UsernamePasswordAuthenticationFilter, extract the username, and password from the request and form an object of UsernamePasswordAuthenticationToken which is an implementation of the Authentication interface. With the object created it invokes authenticate() method of ProviderManager.

5) ProviderManager which is an implementation of AnthenticationManager, identifies the list of Authentication providers available that are supporting a given authentication object style. In the default behavior, authenticate( ) method of the DaoAuthenticationProvider will be invoked by ProviderManager.

6) DaoAuthenticationProvider invokes the method loadUserByUsername() of UserDetailsService to load the user details. Once the user details are loaded, it takes help from the default password encoder implementation to compare the password and validate if the user is authentic or not.

7) At last it returns the Authentication object with the details of the authentication

success or not to ProviderManager.

8) ProviderManager checks if authentication is successful or not. If not, it will try with

other available AuthenticationProviders Otherwise, it simply returns the authentication details to the filters.

9) The Authentication object is stored in the SecurityContext object by the filter

for future use and the response will be returned to the end user.



# Different Ways of Password Management



### Encoding

Encoding is defined as the process of converting data from one form to another and has nothing to do with cryptography. It involves no secret and is completely reversible. Encoding can't be used for securing data. Below are the various publicly available algorithms used for encoding

Ex: ASCII, BASE64, UNICODE

### Encryption

Encryption is defined as the process of transforming data in such a way that guarantees confidentiality. To achieve confidentiality encryption requires the use of a secret which, in

cryptographic terms, we call a "key".

Encryption can be reversible by using decryption with the help of the "key". As long as the "key" is

confidential, encryption can be considered secure.

### Hashing

In hashing, data is converted to the hash value using some hashing function. Data once hashed is non-reversible. One cannot determine the original data from a hash value generated. Given some arbitrary data along with the output of a hashing algorithm, one can verify whether this data matches the original input data without needing to see the original data.

Several algorithms have been developed especially for password hashing:

- bcrypt
- scrypt
- PBKDF2
- NoOpPasswordEncoder
- argon2
