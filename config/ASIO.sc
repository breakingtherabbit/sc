// http://doc.sccode.org/Reference/AudioDeviceSelection.html

/*
SC_PortAudioDriver: PortAudio failed at Pa_OpenStream with error: 'Unanticipated host error'
could not initialize audio.
*/

o = Server.default.options;
o.outDevice_("ASIO : ASIO4ALL v2");
Server.default.reboot;