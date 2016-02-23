# SOS Android Example Project

## Getting Started

### Retrieving the project

Clone the project from [Github](https://github.com/goinstant/android-sos-guides).

After cloning, you should be able to open the project in Android Studio and run any
of the debug variants.

### SOSSettings.properties

In the project explorer you will find an `SOSSettings.properties` file in the assets
folder. This file contains the information you need to start an SOS session.

You **must** update the values in this file to match the information for your
Salesforce organization in order to start a session. The default values are simple
placeholders which will result in an error if used to start a session.

## Project Tour

### Organization and Flavors

The `Android SOS Guides` project is organized into a main tree, which contains
the bulk of the application, as well as SOS-specific files split into four
flavors:

- `simple`: The `simple` flavor demonstrates connecting to an SOS session with the minimum possible amount of integration. There is no customization of the built-in SOS experience.
- `basic`: The `basic` flavor demonstrates some basic configuration of the SOS experience using resource files to override the resources present in the SOS library. This is an easy mechanism for changing the default content without affecting behaviour.
- `advanced`: The `advanced` flavor demonstrates replacing some portions of the SOS logic with custom code. This gives the application greater freedom to customize the SOS experience, but has a higher integration cost and increased level of responsibility for the application developer.
- `fieldServices`: The `fieldServices` flavor launches the SOS session in field services mode, where only the front and back cameras are available (no screen sharing).

Change the build variant from within Android Studio to edit or run the different flavors.

## The Examples

Each example contains extensive inline documentation explaining each line of code. The source code is the best place to get a full understanding of what is being done!

### Common Code

The code in the `main` source tree is shared between all examples. It includes the bulk of the application, including all the activities. The following SOS features are demonstrated in all flavors:

- **Enabling and disabling the SOS button**: The `FloatingActionButton` that is used to launch SOS is disabled during the SOS session. See `MainActivity.java` to understand how this works.
- **Masking the message field**: The message field of the Compose activity is masked using the default masking functionality. The field is visible whenever it has focus for editing and is hidden otherwise.

### Simple

The `simple` flavor shows connecting to an SOS session with no customization. It includes a few lines of code to gather the `SosOptions` from a properties file and start the session.

### Basic

The `basic` flavor includes the same simple connection logic from above, but with some additions.

##### Overrides
There is an `sos_overrides.xml` file that modifies a subset of the overrideable resources to customize
the messaging used in the default dialogs and some of the presentation details for the session,
such as the color and width of the agent annotations.

##### Capturing Toasts
In `ComposeFragment` there is code that shows illustrates how a user would capture a Toast and send it
to the Agent.  Since Toasts live outside of the activity view hierarchy, they are not captured automatically.

### Advanced

The `advanced` flavor includes some of the basic configuration done in the above example, as well as more advanced configuration in the `SosConnector` class. This example overrides some of the dialog presentation logic in order to:

- Bypass the disconnection confirmation prompt (the user will not be prompted when choosing to disconnect).
- Use a snackbar for showing the connection status on the MainActivity.

The Advanced flavor also uses the new Halo UI, which is currently in beta.

### Two-way Camera

The `twoWayCamera` flavor includes the Halo UI with the addition of the camera toggle button. This allows the user to escalate a screen-sharing session to a video call.

### FieldServices

The `fieldServices` flavor launches SOS in field service (camera-only) mode, with no other customization.
