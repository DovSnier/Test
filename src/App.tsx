import React from "react";
import { Platform, StyleSheet, Text, View } from "react-native";

export default class extends React.Component {
  render() {
    let introduce = Platform.select({
      ios: "Press Cmd + R to reload,\n" + "Cmd + D or shake for dev menu",
      android:
        'To reload the app press "r"\n' + 'To open developer menu press "d"',
    });
    return (
      <View style={styles.container}>
        <Text
          style={[
            {
              fontSize: 20,
              textAlign: "center",
              padding: 10,
              marginTop: 45,
              lineHeight: 30,
              //   borderColor: "#EBEBEB",
              //   borderWidth: 1,
              //   borderRadius: 8,
            },
          ]}
        >
          {"Hello, World\n" + new Date().toISOString() + "\n\n"}
        </Text>
        <Text
          style={[
            {
              marginTop: 10,
              padding: 10,
              textAlign: "center",
              alignContent: "center",
              color: "#E6E6E6",
              borderColor: "#EBEBEB",
              borderWidth: 1,
              borderRadius: 8,
            },
          ]}
        >
          {introduce}
        </Text>
      </View>
    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "column",
    padding: 10,
    alignContent: "center",
    backgroundColor: "#FFFFFF",
  },
});
