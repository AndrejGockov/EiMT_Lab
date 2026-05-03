import { useEffect, useState } from 'react';
import { hostRepository } from '../api/hostRepository';
import {Host} from "../api/types/Host";

export const useHostDetail = (id: number) => {
    const [data, setData] = useState<Host | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!id) return;
        hostRepository.getById(id)
            .then(res => setData(res.data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, [id]);

    return { data, loading, error };
};