# mbf4j 

Mbf4j is a Java implementation of Microsoft Bot Framework (https://botframework.com/)
This library allows you to create bots for every platform supported by Bot Framework (which are Telegram, Skype, Facebook Messenger, Slack, Kik, Twilio and some more).

This sample code builds a simple echo bot:
```java
Bot bot = new Bot();
bot
  .setCredentials("api token is supposed to be here", "api secret is supposed to be fere")
  //.setApiRequestFactory(new EmulatorApiRequestFactory("localhost", 65181)) #uncomment this to use bot framework emulator on localhost with port 65181 
  .on(EventTypes.EVENT_TYPE_INCOMING_MESSAGE, (IncomingMessage activity) -> {
      String messageText = activity.text().get();
      Address botAddress = activity.recipient();

      try {
          MessageToSend messageToSend = new MessageToSend(activity.channel(), activity.conversation(), messageText);
          messageToSend.withFrom(botAddress);
          bot.messageSender().send(messageToSend);

      } catch (Exception e) {
          e.printStackTrace();
      }
  })

```
