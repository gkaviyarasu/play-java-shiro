### Play Shiro Integration ###

This is a very simple integration between Apache Shiro, an authorization and authentication platform, and Play 2.4.

This project is a fork from an existing project which was implemented for [play-scala](https://github.com/wsargent/play-shiro). The same demo is modified and made available for play-java
 
### WARNING

This is in no way production level code.  It is an experimental proof of concept, uploaded only out of idle interest; it may
give you a leg up if you're already tasked with integration, but it is not going to help you if you want a turnkey
authentication system.  If that's what you're looking for, I recommend you use SecureSocial or Play-Authenticate.

In particular, Shiro assumes a stateful session strategy, which goes against Play's stateless application.  Internally,
Shiro uses a ThreadLocal to reference the session; shiro-web has a way of disabling session creation, but since Play
isn't built on the Servlet model, I've created analogues for Play that will never create a session.  I tested it using 
Firefox, Safari and Chrome on my Macbook Pro, and was able to log in with different credentials on each browser, but this
is not a guarantee of safety.  

### Starting

To start the application, check the project out from Github, make sure you have Typesafe activator installed, then type

    activator run

And run through the DB evolutions needed.  Then go to http://localhost:9000.

### How it works ###

When the Global object is called, it configures Shiro's security manager with security.SampleRealm.

When you hit the page, Play will look for a token called "email" in request.session, and find the email address
corresponding to that token.  That token is only set if we have successfully logged in.

When you login, User.authenticate will use Shiro's SecurityUtils.currentUser, call login on that, and that will go back
to the Realm for digest checking.  (Note that we use Jasypt to deal with password complexity.)

When you logout, User.logout will call Shiro to invalidate the current session.
