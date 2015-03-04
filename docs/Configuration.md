## Quick Start

To help make SOS Service Cloud configuration drop-dead simple, we have included a easy to use SOS Setup Guide.

### Installing the Pilot Package

You can find the most recent Pilot package at https://sos.goinstant.net/#packages. Select "SOS Quick Setup (Pilot 194)"
and click the "Install" button.

Once the package has been installed, navigate to the ‘SOS Quick Setup’ app and complete Steps 1-4. The SOS Quick Setup app will walk you through:

- Check Org Requirements
- Adding the Presence Widget
- Create New Service Presence Status
- Create New Routing Configuration
- Create New Queue
- Update Permission Set
- Create New SOS Deployment
- Adding Auto Case Pop Logic and Compact Page Layout View

Step 4 provides you with important information that you will need to supply when you are create an SOS session.

![](./SOS_Quick_Setup.png)

### Creating an SOS Session

When you create an SOS session, you need to provide three pieces of information:

- **Liveagent Pod** - The liveagent server that will be used to communicate between the SDK and salesforce (e.g. xx-1.x.salesforce.com).
- **Organization ID** - fifteen character identifier for your entire organization (e.g. 00Dxxxxxxxxxxxx).
- **Deployment ID** - fifteen character deployment identifier used to connect an incoming request to a specific queue (e.g. 0NWxxxxxxxxxxxx).

A fourth piece of information should be included if you are using Autopop:

- **Contact Email** - The email address of a customer that is already authenticated in the app.
SOS will pass the email address to the Service Cloud where it is queried against the Contact Object.
If there is a match, the customer contact will be appended to the Case.
