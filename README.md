Curious about how 𝗝𝗪𝗧 𝗔𝘂𝘁𝗵𝗲𝗻𝘁𝗶𝗰𝗮𝘁𝗶𝗼𝗻 + 𝗦𝗽𝗿𝗶𝗻𝗴 𝗦𝗲𝗰𝘂𝗿𝗶𝘁𝘆 works?

It's a three step process:

	• Client hits a requests , which gets authenticated and a JWT token gets created using spring security prebuild encryptor and library along with some of our manual configs.

	• Once JWT tokens (acces_token + refresh_token ) gets created they are send back to the UI which persist those tokens somewhere locally on client or preferably on servers.

	• Then UI hits backs the request along with JWT token, and gets successfully served with the required API response.

This happens in fraction of time that it's not noticable to user.

These 2 above specified tokens namely access_token and refresh_token has limited lifespans , which is encrypted in the token itself.

lifespan of access_token<<refresh_token

Once access_token gets expired it(UI) requests for new access_token to backend using refresh_token.

Refresh_token too have limited lifespan typically around a month or year as per need.

Once refresh_token gets expired , used is asked to relogin to the system hence following the above lifecycle again and again. 


Wanna  checkout how actual code of it using spring security looks like??

Then checkout my below Github repository:


