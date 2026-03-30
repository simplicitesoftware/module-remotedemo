![Logo](https://platform.simplicite.io/logos/standard/logo250.png)
* * *

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=simplicite-modules-RemoteDemo&metric=alert_status)](https://sonarcloud.io/dashboard?id=simplicite-modules-RemoteDemo)

### Introduction

Remote business object examples (based on the Demo application)

### Import

To import this module:

- Create a module named `RemoteDemo`
- Set the settings as:

```json
{
	"type": "git",
	"origin": {
		"uri": "https://github.com/simplicitesoftware/module-remotedemo.git"
	}
}
```

- Click on the _Import module_ button

### Configure

There is one system parameters to configure:

- The `REMOTEDEMO_CREDENTIALS` in which you must set your credentials to the Demo instance.

> **Note**: it is possible to ovveride these default parameters per user using corresponding user parameters.

