package io.sparkled.viewmodel.sequence;

import io.sparkled.model.entity.SequenceStatus;
import io.sparkled.viewmodel.sequence.channel.SequenceChannelViewModel;

import java.util.List;
import java.util.Objects;

public class SequenceViewModel {
    private Integer id;
    private SequenceStatus status;
    private String name;
    private int durationFrames;
    private int framesPerSecond = 60;
    private List<SequenceChannelViewModel> channels;

    public Integer getId() {
        return id;
    }

    public SequenceViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SequenceStatus getStatus() {
        return status;
    }

    public SequenceViewModel setStatus(SequenceStatus status) {
        this.status = status;
        return this;
    }

    public SequenceViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getDurationFrames() {
        return durationFrames;
    }

    public SequenceViewModel setDurationFrames(int durationFrames) {
        this.durationFrames = durationFrames;
        return this;
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public SequenceViewModel setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        return this;
    }

    public List<SequenceChannelViewModel> getChannels() {
        return channels;
    }

    public SequenceViewModel setChannels(List<SequenceChannelViewModel> channels) {
        this.channels = channels;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SequenceViewModel that = (SequenceViewModel) o;
        return durationFrames == that.durationFrames &&
                framesPerSecond == that.framesPerSecond &&
                Objects.equals(id, that.id) &&
                status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(channels, that.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, name, durationFrames, framesPerSecond, channels);
    }

    @Override
    public String toString() {
        return "SequenceViewModel{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", durationFrames=" + durationFrames +
                ", framesPerSecond=" + framesPerSecond +
                ", channels=" + channels +
                '}';
    }
}