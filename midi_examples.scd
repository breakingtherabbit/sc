MIDIClient.init;


(
MIDIClient.init;
MIDIIn.connectAll;
m = MIDIFunc.noteOn({ |vel, num|
	"note % @ velocity %\n".postf(num, vel);
});
)

m.free;

(
MIDIClient.init;
m = MIDIOut(0, MIDIClient.destinations.at(0).uid);
	m.noteOn(0, 60, 60);
)

{SinOsc.ar(LFNoise0.kr(10).range(500, 1500), mul: 0.1)}.play

/*
*/

g = { SinOsc.ar(440, 0, 0.1) + PinkNoise.ar(0.01) }.play;
g.free;

SynthDef.new(\sinpk, { Out.ar(0, SinOsc.ar(440, 0, 0.1) + PinkNoise.ar(0.01)) }).play;

SynthDef.new(\sinpk, { arg freq = 440;
	Out.ar(0, Pan2.ar(SinOsc.ar(freq, 0, 0.1) + PinkNoise.ar(0.01)));
}).add;

h = Synth(\sinpk, [\freq, 440]);
h.set("freq", (69).midicps);
MIDIdef.noteOn(\changefreq, {arg val, num, chan, src;
	h.set("freq", (num).midicps);
});