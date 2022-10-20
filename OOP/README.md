# Object-Oriented Programming

```sc

Platform.userExtensionDir;

MyClass : Object {

	instanceMethod { | argument |
		this.anotherInstanceMethod(argument)
	}

	anotherInstanceMethod { | argument |
		"Hello instance".postln
	}

}

```