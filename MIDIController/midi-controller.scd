(
MIDIClient.init;
MIDIIn.connectAll;
f = { | vel, num | MIDIOut(0, MIDIClient.destinations.at(1).uid).noteOn(0, num, vel) };
/*m = MIDIFunc.noteOn({ |vel, num|
	MIDIOut(0, MIDIClient.destinations.at(0).uid).noteOn(0, num, vel);
});*/
m = MIDIFunc.noteOn(f);
)

MIDIClient.init;
MIDIClient.list;
MIDIClient.destinations.at(1);
MIDIClient.disposeClient;
MIDIIn.disconnectAll;
m.free;

/* VIOLÃO
   e = 64
   B = 59
   G = 55
   D = 50
   A = 45
   E = 40
*/

(
var e0, b0, g0, d0, a0, e1, x;

e0 = 64;
b0 = 59;
g0 = 55;
d0 = 50;
a0 = 45;
e1 = 40;
x = a0 + 3; // c
y = x + 24;

MIDIClient.init;
m = MIDIOut(0, MIDIClient.destinations.at(0).uid);

m.noteOn(0, x, 60);
m.noteOn(0, x + 4 - 1, 60);
m.noteOn(0, x + 7, 60);

// m.noteOn(0, y, 60);
// m.noteOn(0, y + 4, 60);
// m.noteOn(0, y + 7, 60);

// m.noteOn(0, b0, 60);
// m.noteOn(0, g0, 60);
// m.noteOn(0, d0, 60);
// m.noteOn(0, a0, 60);
// m.noteOn(0, e1, 60);
)

{SinOsc.ar(LFNoise0.kr(10).range(500, 1500), mul: 0.1)}.play

/*
*/

g = { SinOsc.ar(440, 0, 0.1) + PinkNoise.ar(0.01) }.play;
g.free;

SynthDef.new(\sinpk, {
	Out.ar(0, SinOsc.ar(440, 0, 0.1) + PinkNoise.ar(0.01)) }).play;

SynthDef.new(\sinpk, { arg freq = 440;
	Out.ar(0, Pan2.ar(SinOsc.ar(freq, 0, 0.1) + PinkNoise.ar(0.01)));
}).add;

h = Synth(\sinpk, [\freq, 440]);
h.set("freq", (69).midicps);
MIDIdef.noteOn(\changefreq, {arg val, num, chan, src;
	h.set("freq", (num).midicps);
});