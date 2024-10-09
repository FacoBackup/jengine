package com.pine.repository.voxelization;

import com.pine.SerializableRepository;
import com.pine.injection.PBean;
import com.pine.inspection.Inspectable;
import com.pine.inspection.MutableField;
import com.pine.service.resource.ssbo.ShaderStorageBufferObject;
import com.pine.service.svo.SparseVoxelOctree;
import com.pine.theme.Icons;
import org.joml.Vector3f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@PBean
public class VoxelizerRepository extends Inspectable implements SerializableRepository {
    public transient IntBuffer octreeMemBuffer = null;
    public transient FloatBuffer voxelDataMemBuffer = null;
    public transient ShaderStorageBufferObject octreeSSBO;
    public transient ShaderStorageBufferObject voxelDataSSBO;

    @MutableField(label = "Voxel grid resolution", max = 512, min = 32)
    public int gridResolution = 128;

    @MutableField(label = "Scene size", max = -1)
    public float size = 1;

    @MutableField(label = "Center")
    public Vector3f center = new Vector3f(0);

    @MutableField(label = "Max depth", min = 1, max = 10)
    public int maxDepth = 4;

    public SparseVoxelOctree sparseVoxelOctree;


    @Override
    public String getTitle() {
        return "Scene voxelization";
    }

    @Override
    public String getIcon() {
        return Icons.apps;
    }
}
