package com.example.duanlon.service;

import com.example.duanlon.model.TrackEntry;

public interface ITrackEntryService extends IAbstractService<TrackEntry> {
    TrackEntry createTrackEntry(TrackEntry trackEntry);
}
