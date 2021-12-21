import { AppRegistry } from "react-native";
import { name as applicationName } from './app.json'
import App from './src/App'

AppRegistry.registerComponent(
    applicationName,
    () => App
);
